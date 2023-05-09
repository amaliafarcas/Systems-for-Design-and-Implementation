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
    Tooltip, Typography,
} from "@mui/material";

import {useEffect, useState} from "react";
import {BACKEND_API_URL} from "../../constants";
import {ShelterReportAnimalsVaccinated} from "../../models/ShelterReportAnimalsVaccinated";
import {ArrowBackIosNew, ArrowForwardIos} from "@mui/icons-material";


export const SheltersStatistics = () => {

    const [loading, setLoading] = useState(true)
    const [shelters, setShelters] = useState([]);

    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);

    const startIndex = (page - 1) * pageSize;

    /*    useEffect(() => {
            fetch(`${BACKEND_API_URL}/shelter/reportAnimals`)
                .then(res => res.json())
                .then(data => {setShelters(data); setLoading(false);})
        }, []);

        console.log(shelters);*/

    const fetchShelters = async () => {
        setLoading(true);
        const response = await fetch(
            `${BACKEND_API_URL}/shelter/reportAnimals?page=${page}&size=${pageSize}`
        );
        const {
            content, pageable, last, totalPages, totalElements,
            size, number, sort, first, numberOfElements, empty
        } = await response.json();
        setShelters(content);
        setLoading(false);
    };

    useEffect(() => {
        fetchShelters();
    }, [page]);

    return (

        <Container>
            <h1 style={{marginTop: "65px", color: 'black'}}>Shelters Animals vaccination rate</h1>

            {loading && <CircularProgress/>}

            {!loading && shelters.length == 0 && <div>No reports found</div>}

            {!loading && shelters.length > 0 && (

                <>
                    <TableContainer component={Paper}>
                        <Table sx={{minWidth: 800}} aria-label="simple table" style={{backgroundColor: "whitesmoke"}}>
                            <TableHead>
                                <TableRow>
                                    <TableCell align="center"
                                               style={{color: "black", fontWeight: 'bold'}}>Crt.</TableCell>
                                    <TableCell align="center" style={{color: "black", fontWeight: 'bold'}}>Shelter
                                        Name</TableCell>
                                    <TableCell align="center"
                                               style={{color: "black", fontWeight: 'bold'}}>Rate</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {shelters.map((report: ShelterReportAnimalsVaccinated, index) => (
                                    <TableRow key={report.id}>
                                        <TableCell component="th" scope="row">
                                            {startIndex + index + 1}
                                        </TableCell>
                                        <TableCell align="center">
                                            {report.shelter}
                                        </TableCell>
                                        <TableCell align="center">{report.ratioVaccinated}</TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>


                    <div style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                        <IconButton style={{color: "black", outline: "none"}} disabled={page === 1}
                                    onClick={() => setPage(page - 1)}>
                            <Tooltip title="Previous" arrow>
                                <ArrowBackIosNew style={{color: "black", fontSize: "20px"}}/>
                            </Tooltip>
                        </IconButton>

                        <Typography style={{fontWeight: 'bold'}}>
                            {page}
                        </Typography>

                        <IconButton style={{color: "black", outline: "none"}} disabled={page === 100001}
                                    onClick={() => setPage(page + 1)}>
                            <Tooltip title="Next" arrow>
                                <ArrowForwardIos style={{color: "black", fontSize: "20px"}}/>
                            </Tooltip>
                        </IconButton>
                    </div>
                </>
            )
            }
        </Container>

    );
};