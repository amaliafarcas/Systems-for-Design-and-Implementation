import {Box, Button, Card, CardActions, CardContent, Container, IconButton, Tooltip, Typography} from "@mui/material";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { Shelter } from "../../models/Shelter";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { FullShelter } from "../../models/FullShelter";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import {BACKEND_API_URL} from "../../constants";
import DeleteIcon from "@mui/icons-material/Delete";
import UpgradeIcon from "@mui/icons-material/Upgrade";


export const ShelterDetails = () => {

    const { shelterId, page, pageSize } = useParams();
    console.log(page);
    const [shelter, setShelter] = useState<FullShelter>();

    useEffect(() => {
        const fetchShelter = async () => {
            const response = await fetch(`${BACKEND_API_URL}/shelter/${shelterId}`);
            const shelter = await response.json();
            setShelter(shelter);
            console.log(shelter);
        };
        fetchShelter();
    }, [shelterId]);

    return (
        <Container>
            <Card style={{backgroundColor:"whitesmoke"}}>
                <CardContent>
                    <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', paddingRight:3}}>
                        <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter`}>
                            <ArrowBackIcon />
                        </IconButton>{" "}
                        <h2 style={{textAlign:"left", fontWeight:'bold'}}>{shelter?.name}</h2>
                    </Box>
                    {/*<p  style={{textAlign:"left", fontWeight:'bold'}}>Name: {shelter?.name}</p>*/}
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>City: {shelter?.city}</p>
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>Postal Code: {shelter?.postalCode}</p>
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>Phone Number: (+){shelter?.phoneNumber}</p>
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>Capacity: {shelter?.capacity}</p>
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>Animals</p>
                    <ul style={{textAlign:"left", fontWeight:'bold'}}>
                        {shelter?.animals?.map((animal) => (
                            <li key={animal.id}>{animal.name} {animal.microchipNumber}</li>
                        ))}
                    </ul>
                </CardContent>

                <CardActions>
                    <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelterId}/edit`}>
                        <EditIcon sx={{ color: "navy" }}/>
                    </IconButton>

                    <IconButton component={Link} sx={{ mr: 3 }} to={`/shelter/${shelterId}/delete`}>
                        <DeleteIcon sx={{ color: "darkred" }} />
                    </IconButton>
                </CardActions>

            </Card>
        </Container>
    );
};