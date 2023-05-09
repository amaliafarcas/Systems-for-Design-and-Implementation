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
    Typography,
    Box, Input, TextField, Button
} from "@mui/material";

import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from '@mui/icons-material/Delete';
import React, { useEffect, useState } from "react";
import { Shelter } from "../../models/Shelter";
import {Link, useParams} from "react-router-dom";
import AddIcon from "@mui/icons-material/Add";
import SortIcon from '@mui/icons-material/Sort';
import VisibilityIcon from '@mui/icons-material/Visibility';
import { BACKEND_API_URL } from "../../constants";
import {ArrowBackIos, ArrowBackIosNew, ArrowForwardIos} from "@mui/icons-material";
import {Paginator} from "../pagination/Paginator";


export const SheltersShowAll = () => {

    const[loading, setLoading] = useState(true)
    const[shelters, setShelters] = useState([]);

    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);
    const [totalRows, setTotalRows] = useState(0);

    const startIndex = (page-1) * pageSize;

    const [isLastPage, setIsLastPage] = useState(false);


    const [capacity, setCapacity] = useState(0);


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

/*    const [linkShelters, setLinkShelters] = useState("");
    const [linkCount, setLinkCount] = useState("");

    const fetchShelters = async () => {
        setLoading(true);
        if (capacity == 0) {
            setLinkShelters(`${BACKEND_API_URL}/shelter?page=${page - 1}&size=${pageSize}`);
            setLinkCount(`${BACKEND_API_URL}/shelter/totalRecords`);
        } else {
            setLinkShelters(`${BACKEND_API_URL}/shelter/greaterCapacity?capacity=${capacity}&page=${page - 1}&size=${pageSize}`);
            setLinkCount(`${BACKEND_API_URL}/shelter/recordsGreaterCapacity?capacity=${capacity}`);
        }
        const response = await fetch(
            `${linkShelters}`
        );

        const {
            content, pageable, last, totalPages, totalElements,
            size, number, sort, first, numberOfElements, empty
        } = await response.json();
        setShelters(content);
        const countRecordsResponse = await fetch(`${linkCount}`)
        const countRecords = await countRecordsResponse.json();
        setTotalRows(countRecords);
        setLoading(false);
    };*/

    const fetchShelters = async () => {
        setLoading(true);
        if(capacity == 0){
            const response = await fetch(
                `${BACKEND_API_URL}/shelter?page=${page-1}&size=${pageSize}`
            );
            const { content, pageable, last, totalPages, totalElements,
                size, number, sort,  first, numberOfElements, empty } = await response.json();
            setShelters(content);
            const countRecordsResponse = await fetch(`${BACKEND_API_URL}/shelter/totalRecords`)
            const countRecords = await countRecordsResponse.json();
            setTotalRows(countRecords);
            setLoading(false);
        }
        else{
            const response = await fetch(
                `${BACKEND_API_URL}/shelter/greaterCapacity?capacity=${capacity}&page=${page-1}&size=${pageSize}`
            );
            const { content, pageable, last, totalPages, totalElements,
                size, number, sort,  first, numberOfElements, empty } = await response.json();
            setShelters(content);
            const countRecordsResponse = await fetch(`${BACKEND_API_URL}/shelter/recordsGreaterCapacity?capacity=${capacity}`)
            const countRecords = await countRecordsResponse.json();
            setTotalRows(countRecords);
            setLoading(false);
        }

    };

    useEffect(() => {
        fetchShelters();
    }, [page-1]);

    const sortShelters = () => {

        const sortedShelters = [...shelters].sort((a: Shelter, b: Shelter) => {
            if (a.capacity < b.capacity) {
                return -1;
            }
            if (a.capacity > b.capacity) {
                return 1;
            }
            return 0;
        })
        console.log(sortedShelters);
        setShelters(sortedShelters);
    }

    return (

        <Container>
            <h1 style={{marginTop:"65px", color:'black'}}>All Shelters</h1>

            {loading && <CircularProgress />}

            {!loading && shelters.length === 0 && <div>No shelters found</div>}

            {!loading && shelters.length > 0 && (
                <>
                    <div style={{ display: 'flex' }}>
                        <TextField style={{color:"#2471A3", fontWeight:'bold', paddingRight:'15px', borderBlockColor:'black'}}
                                   id="standard-basic"
                                   label="Capacity"
                                   variant="standard"
                                   sx={{ mb: 2 }}
                                   onChange={(event) => {setCapacity(Number(event.target.value)); setPage(1);}}
                            />
                        <Button variant="text"
                                style={{color:"black",
                                    paddingTop:'22px',
                                    paddingLeft: '10px',
                                    paddingRight: '10px'}}
                                sx={{ mb: 2 }}
                                onClick={() => fetchShelters()}>
                            Filter
                        </Button>
                    </div>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 800 }} aria-label="simple table" style={{backgroundColor:"whitesmoke"}}>
                        <TableHead>
                            <TableRow>
                                <TableCell align="center" style={{color:"black", fontWeight:'bold'}}>Crt.</TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight:'bold'}}>Name</TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight: 'bold'}}>City</TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight: 'bold'}}>Postal Code</TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight: 'bold'}}>Phone Number (+)</TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight: 'bold'}}>
                                    Capacity
                                    <IconButton sx={{color:"black", paddingLeft:2, fontSize:"20px", width:"20px", '&:focus': {
                                            outline: "none"
                                        } }} onClick={sortShelters}>
                                        <Tooltip title="Sort" arrow>
                                            <SortIcon style={{color:"black", fontSize:"20px"}} />
                                        </Tooltip>
                                    </IconButton>

                                </TableCell>
                                <TableCell align="center" style={{color:"black", fontWeight: 'bold'}}>Operations
                                    <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/add`}>
                                        <Tooltip title="Add a new shelter" arrow>
                                            <AddIcon style={{color:"black", fontSize:"20px"}} />
                                        </Tooltip>
                                    </IconButton></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {shelters.map((shelter:Shelter, index) => (
                                <TableRow key={shelter.id}>
                                    <TableCell component="th" scope="row">
                                        {startIndex + index + 1}
                                    </TableCell>
                                    <TableCell align="center">
                                        {shelter.name}
                                        </TableCell>
                                    <TableCell align="center">{shelter.city}</TableCell>
                                    <TableCell align="center">{shelter.postalCode}</TableCell>
                                    <TableCell align="center">{shelter.phoneNumber}</TableCell>
                                    <TableCell align="center">{shelter.capacity}</TableCell>
                                    <TableCell align="center">
                                        <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelter.id}`}>
                                            <VisibilityIcon  style={{color:"black", fontSize:"20px"}}/>
                                        </IconButton>

                                        <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelter.id}/edit`}>
                                            <EditIcon sx={{ color: "navy" }}/>
                                        </IconButton>

                                        <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelter.id}/delete`}>
                                            <DeleteIcon sx={{ color: "darkred" }} />
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>

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