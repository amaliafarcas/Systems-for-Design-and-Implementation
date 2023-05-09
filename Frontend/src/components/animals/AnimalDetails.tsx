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


export const AnimalDetails = () => {

    const { animalId, page, pageSize } = useParams();
    //console.log(animalId);
    const [animal, setAnimal] = useState<Animal>();
    const [medicalRecord, setMedicalRecord] = useState<MedicalRecord>();
    const [shelter, setShelter] = useState<Shelter>();

    useEffect(() => {
        const fetchAnimal = async () => {
            const response = await fetch(`${BACKEND_API_URL}/animal/${animalId}`);
            const animal = await response.json();
            setAnimal(animal);
            console.log(animal.volunteersAssigned);

            const responseMedicalRecord = await fetch(`${BACKEND_API_URL}/medicalRecord/${animal.medicalRecord.id}`);
            const medicalRecord = await responseMedicalRecord.json();
            setMedicalRecord(medicalRecord);
            //console.log(medicalRecord);

            const responseShelter = await fetch(`${BACKEND_API_URL}/shelter/${animal.shelter.id}`);
            const shelter = await responseShelter.json();
            setShelter(shelter);
            //console.log(shelter);

        };
        fetchAnimal();
    }, [animalId]);

    return (
        <Container>
            <Card style={{backgroundColor:"whitesmoke"}}>
    <CardContent>
        <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', paddingRight:3}}>
    <IconButton component={Link} sx={{ mr: 3 }} to={`/animal`}>
    <ArrowBackIcon />
    </IconButton>{" "}
    <h2 style={{textAlign:"left", fontWeight:'bold'}}>{animal?.name}</h2>
    </Box>
    {/*<p  style={{textAlign:"left", fontWeight:'bold'}}>Name: {shelter?.name}</p>*/}
    <p  style={{textAlign:"left", fontWeight:'bold'}}>Microchip Number: {animal?.microchipNumber}</p>
    <p  style={{textAlign:"left", fontWeight:'bold'}}>Age: {medicalRecord?.age}</p>
    <p  style={{textAlign:"left", fontWeight:'bold'}}>Weight: {medicalRecord?.weight}</p>
        <p style={{ textAlign: "left", fontWeight: "bold" }}>
            Date of Birth: {new Date(medicalRecord?.dateOfBirth ?? "").toLocaleDateString()}
        </p>
        <p  style={{textAlign:"left"}}><b>Shelter : </b>{shelter?.name} - {shelter?.city}</p>
        <p  style={{textAlign:"left", fontWeight:'bold'}}>Date Brought In: {new Date(animal?.dayBroughtIn ?? "").toLocaleDateString()}</p>
        <p  style={{textAlign:"left", fontWeight:'bold'}}>Date Adopted: {animal?.dateAdopted ? new Date(animal?.dateAdopted ?? "").toLocaleDateString() : "-"}</p>
        <p  style={{textAlign:"left", fontWeight:'bold'}}>Additional Information: {medicalRecord?.additionalInformation}</p>
        <p  style={{textAlign:"left", fontWeight:'bold'}}>Volunteers Assigned: </p>
        <ul style={{textAlign:"left", fontWeight:'bold'}}>
            {animal?.volunteersAssigned?.map((volunteer) => (
                <li key={volunteer.t.id}>{volunteer.t.name}</li>
            ))}
        </ul>

    </CardContent>

    <CardActions>
    <IconButton component={Link} sx={{ mr: 3 }} to={`/animal/${animalId}/edit`}>
    <EditIcon sx={{ color: "navy" }}/>
    </IconButton>

    <IconButton component={Link} sx={{ mr: 3 }} to={`/animal/${animalId}/delete`}>
    <DeleteIcon sx={{ color: "darkred" }} />
    </IconButton>
    </CardActions>

    </Card>
    </Container>
);
};