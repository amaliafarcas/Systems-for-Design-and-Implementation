import {
    TableContainer,
    Paper,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    CircularProgress,
    Container,
    IconButton,
    Tooltip,
    Typography
} from "@mui/material";

import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from '@mui/icons-material/Delete';
import { useEffect, useState } from "react";
import { Animal } from "../../models/Animal";
import { Link } from "react-router-dom";
import AddIcon from "@mui/icons-material/Add";
import SortIcon from '@mui/icons-material/Sort';
import { BACKEND_API_URL } from "../../constants";
import {Shelter} from "../../models/Shelter";
import VisibilityIcon from "@mui/icons-material/Visibility";
import {ArrowBackIosNew, ArrowForwardIos} from "@mui/icons-material";
import {Volunteer} from "../../models/Volunteer";
import {Paginator} from "../pagination/Paginator";

export const VolunteersShowAll = () => {
    const [loading, setLoading] = useState(true);
    const [volunteers, setVolunteers] = useState([]);
    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);
    const [totalRows, setTotalRows] = useState(0);

    const startIndex = (page - 1) * pageSize;

    const [isLastPage, setIsLastPage] = useState(false);

    const setCurrentPage = (newPage: number) => {
        setPage(newPage);
    }

    const goToNextPage = () => {
        if (isLastPage) {
            return;
        }

        setPage(page + 1);
    }

    const goToPrevPage = () => {
        if (page === 1) {
            return;
        }

        setPage(page - 1);
    }


    const fetchVolunteers = async () => {
        setLoading(true);
        const response = await fetch(
            `${BACKEND_API_URL}/volunteer?page=${page-1}&size=${pageSize}`
        );
        const { content, pageable, last, totalPages, totalElements,
            size, number, sort,  first, numberOfElements, empty } = await response.json();
        setVolunteers(content);
        console.log(volunteers);

        const countRecordsResponse = await fetch(`${BACKEND_API_URL}/volunteer/totalRecords`)
        const countRecords = await countRecordsResponse.json();
        setTotalRows(countRecords);

        setLoading(false);
    };

    useEffect(() => {
        fetchVolunteers();
    }, [page-1]);

/*    const sortAnimals = () => {
        const sortedAnimals = [...volunteers].sort((a: Animal, b: Animal) => {
            if (a.name < b.name) {
                return -1;
            }
            if (a.name > b.name) {
                return 1;
            }
            return 0;
        });
        console.log(sortedAnimals);
        setVolunteers(sortedAnimals);
    }*/

    return (
        <Container>
            <h1 style={{ marginTop: "65px", color: 'black' }}>All Volunteers</h1>

            {loading && <CircularProgress />}

            {!loading && volunteers.length === 0 && <div>No volunteers found</div>}

            {!loading && volunteers.length > 0 && (
                <>
                    <TableContainer component={Paper}>
                        <Table sx={{ minWidth: 800 }} aria-label="simple table" style={{ backgroundColor: "whitesmoke" }}>
                            <TableHead>
                                <TableRow>
                                    <TableCell align="center" style={{ color: "black", fontWeight: 'bold' }}>Crt.</TableCell>
                                    <TableCell align="center" style={{ color: "black", fontWeight: 'bold' }}>Name</TableCell>
                                    <TableCell align="center" style={{ color: "black", fontWeight: 'bold' }}>Code</TableCell>
                                    <TableCell align="center" style={{ color: "black", fontWeight: 'bold' }}>Specialist</TableCell>
                                    <TableCell align="center" style={{ color: "black", fontWeight: 'bold' }}>Operations
                                        <IconButton component={Link} sx={{ mr: 3 }} to={`/volunteer/add`}>
                                            <Tooltip title="Add a new volunteer" arrow>
                                                <AddIcon style={{ color: "black", fontSize: "20px" }} />
                                            </Tooltip>
                                        </IconButton></TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {volunteers.map((volunteer:Volunteer, index) => (
                                    <TableRow key={volunteer.id}>
                                        <TableCell component="th" scope="row">
                                            {startIndex + index + 1}
                                        </TableCell>
                                        <TableCell align="center">
                                            {volunteer.name}
                                        </TableCell>
                                        <TableCell align="center">{volunteer.code}</TableCell>
                                        <TableCell align="center">{volunteer.specialist ? "true" : "false" }</TableCell>
                                        <TableCell align="center">
                                            <IconButton component={Link} sx={{ mr: 3 }} to={`/volunteer/${volunteer.id}`}>
                                                <VisibilityIcon  style={{color:"black", fontSize:"20px"}}/>
                                            </IconButton>

                                            <IconButton component={Link} sx={{ mr: 3 }} to={`/volunteer/${volunteer.id}/edit`}>
                                                <EditIcon sx={{ color: "navy" }}/>
                                            </IconButton>

                                            <IconButton component={Link} sx={{ mr: 3 }} to={`/volunteer/${volunteer.id}/delete`}>
                                                <DeleteIcon sx={{ color: "darkred" }} />
                                            </IconButton>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>

                    {/*<div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                        <IconButton style={{color:"black", outline: "none"}} disabled={page === 1}
                                    onClick={() => setPage(page - 1)}>
                            <Tooltip title="Previous" arrow>
                                <ArrowBackIosNew style={{color:"black", fontSize:"20px"}} />
                            </Tooltip>
                        </IconButton>

                        <Typography style={{ fontWeight: 'bold'}}>
                            {page}
                        </Typography>

                        <IconButton style={{color:"black", outline: "none"}} disabled={page === 100001}
                                    onClick={() => setPage(page + 1)}>
                            <Tooltip title="Next" arrow>
                                <ArrowForwardIos style={{color:"black", fontSize:"20px"}} />
                            </Tooltip>
                        </IconButton>
                    </div>*/}

                    <Paginator
                        rowsPerPage={pageSize}
                        totalRows={totalRows}
                        currentPage={page}
                        isFirstPage={page === 1}
                        isLastPage={isLastPage}
                        setPage={setCurrentPage}
                        goToNextPage={goToNextPage}
                        goToPrevPage={goToPrevPage}
                    />
                </>
            )
            }
        </Container>

    );
};