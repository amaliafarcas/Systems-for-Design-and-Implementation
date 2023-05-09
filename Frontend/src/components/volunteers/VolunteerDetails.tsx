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
import {Animal} from "../../models/Animal";
import {MedicalRecord} from "../../models/MedicalRecord";
import {Volunteer} from "../../models/Volunteer";


export const VolunteerDetails = () => {

    const { volunteerId, page, pageSize } = useParams();
    //console.log(animalId);
    const [volunteer, setVolunteer] = useState<Volunteer>();

    useEffect(() => {
        const fetchVolunteer = async () => {
            const response = await fetch(`${BACKEND_API_URL}/volunteer/${volunteerId}`);
            const volunteer = await response.json();
            setVolunteer(volunteer);
        };
        fetchVolunteer();
    }, [volunteerId]);

    return (
        <Container>
            <Card style={{backgroundColor:"whitesmoke"}}>
                <CardContent>
                    <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', paddingRight:3}}>
                        <IconButton component={Link} sx={{ mr: 3 }} to={`/volunteer`}>
                            <ArrowBackIcon />
                        </IconButton>{" "}
                        <h2 style={{textAlign:"left", fontWeight:'bold'}}>{volunteer?.name}</h2>
                    </Box>
                    {/*<p  style={{textAlign:"left", fontWeight:'bold'}}>Name: {shelter?.name}</p>*/}
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>Code: {volunteer?.code}</p>
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>Date of Birth: {new Date(volunteer?.dateOfBirth ?? "").toLocaleDateString()}</p>
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>Email: {volunteer?.email}</p>
                    <p  style={{textAlign:"left", fontWeight:'bold'}}>IsSpecialist: {volunteer?.specialist ? "true" : "false" }</p>
                </CardContent>

                <CardActions>
                    <IconButton component={Link} sx={{ mr: 3 }} to={`/volunteer/${volunteerId}/edit`}>
                        <EditIcon sx={{ color: "navy" }}/>
                    </IconButton>

                    <IconButton component={Link} sx={{ mr: 3 }} to={`/volunteer/${volunteerId}/delete`}>
                        <DeleteIcon sx={{ color: "darkred" }} />
                    </IconButton>
                </CardActions>

            </Card>
        </Container>
    );
};