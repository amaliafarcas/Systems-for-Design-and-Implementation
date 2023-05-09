import {
    Autocomplete,
    Box,
    Button,
    Card,
    CardActions,
    CardContent, Checkbox,
    CircularProgress,
    Container, FormControlLabel,
    IconButton,
    Snackbar,
    TextField, Tooltip, Typography
} from "@mui/material";
import React, {useEffect, useState} from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import {Shelter} from "../../models/Shelter";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";
import {FullShelter} from "../../models/FullShelter";
import {BACKEND_API_URL} from "../../constants";
import MuiAlert, {AlertProps} from "@mui/material/Alert";
import UpgradeIcon from '@mui/icons-material/Upgrade';
import AddIcon from "@mui/icons-material/Add";


const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(
    props,
    ref,
) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});


export const AnimalUpdate = () => {
    const [errorMessage, setErrorMessage] = useState("");
    const [showNotification, setShowNotification] = useState(false);

    const navigate = useNavigate();
    const {animalId} = useParams();

    const [loading, setLoading] = useState(true)
    const [animal, setAnimal] = useState({
        id: animalId,
        name: "",
        microchipNumber:0,
        shelter: 0,
        dayBroughtIn: "",
        dateAdopted: ""
    });

    const [medicalRecord, setMedicalRecord] = useState({
        id: 0,
        dateOfBirth: "",
        weight: 0,
        vaccine: false,
        specialCare: false,
        additionalInformation: ""
    });

    useEffect(() => {
        const fetchAnimal = async () => {
            const response = await fetch(`${BACKEND_API_URL}/animal/${animalId}`);
            const animal = await response.json();
            setAnimal({
                id: animalId,
                name: animal.name,
                microchipNumber: animal.microchipNumber,
                shelter: animal.shelterId,
                dayBroughtIn: animal.dayBroughtIn,
                dateAdopted: animal.dateAdopted
            })

            const responseMedicalRecord = await fetch(`${BACKEND_API_URL}/medicalRecord/${animal.medicalRecord.id}`);
            const medicalRecord = await responseMedicalRecord.json();
            setMedicalRecord({
                id: animal.medicalRecord,
                dateOfBirth: medicalRecord.dateOfBirth,
                weight: medicalRecord.weight,
                vaccine: medicalRecord.vaccine,
                specialCare: medicalRecord.specialCare,
                additionalInformation: medicalRecord.additionalInformation
            })

            setLoading(false);
            console.log(animal);
        };
        fetchAnimal();
    }, [animalId]);

    const updateAnimal = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        try {
            await axios.put(`${BACKEND_API_URL}/animal`, animal);
            await axios.put(`${BACKEND_API_URL}/medicalRecord`, medicalRecord);
            navigate(`/animal/${animalId}`);
        } catch (error) {
            console.log(error);
            setErrorMessage("Animal could not be updated.  Make sure the information is correct. ");
            setShowNotification(true);
        }
    };


    return (
        <Container>

            {showNotification && (
                <Snackbar open={!!errorMessage} autoHideDuration={6000} onClose={() => setShowNotification(false)}>
                    <Alert severity="error" sx={{width: '100%'}}>
                        {errorMessage}
                    </Alert>
                </Snackbar>

            )}

            <Card>
                <CardContent>

                    <form onSubmit={updateAnimal}>
                        <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', paddingBlockEnd: 3}}>
                            <IconButton component={Link} sx={{mr: 3}} to={`/animal`}>
                                <ArrowBackIcon/>
                            </IconButton>{" "}
                            <Typography variant="h6" sx={{flexGrow: 1, textAlign: 'center'}}>
                                <b>Update {animal.name} Animal</b>
                            </Typography>

                            <Button type="submit" color="inherit" sx={{color: 'black'}}>
                                <Tooltip title="Add" arrow>
                                    <AddIcon/>
                                </Tooltip>
                            </Button>
                        </Box>
                        <TextField value={animal.name} style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="name"
                                   label="Name"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   onChange={(event) => setAnimal({...animal, name: event.target.value})}
                        />

                        <TextField value={medicalRecord.dateOfBirth ? new Date(medicalRecord.dateOfBirth).toISOString().slice(0,10) : ''} style={{color: "#2471A3", fontWeight: "bold"}}
                                    id="dateOfBirth"
                                   label="Date of Birth"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   type="date"  // set the input type to date
                                   InputLabelProps={{shrink: true}}  // this will shrink the label when the user selects a date
                                   onChange={(event) => setMedicalRecord({
                                       ...medicalRecord,
                                       dateOfBirth: event.target.value
                                   })}
                        />


                        <TextField value={medicalRecord.weight} style={{color: "#2471A3", fontWeight: "bold"}}
                                   id="weight"
                                   label="Weight"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   onChange={(event) => setMedicalRecord({
                                       ...medicalRecord,
                                       weight: parseInt(event.target.value)
                                   })}
                        />

                        <TextField value={animal.microchipNumber} style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="microchipNumber"
                                   label="Microchip Number"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   disabled // add disabled attribute to make the TextField uneditable
                        />

                        <TextField value={animal.dayBroughtIn ? new Date(animal.dayBroughtIn).toISOString().slice(0,10) : ''} style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="dayBroughtIn"
                                   label="Day Brought In"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   type="date"  // set the input type to date
                                   InputLabelProps={{shrink: true}}  // this will shrink the label when the user selects a date
                                   onChange={(event) => setAnimal({...animal, dayBroughtIn: event.target.value})}
                        />

                        <TextField value={animal.dateAdopted ? new Date(animal.dateAdopted).toISOString().slice(0,10) : ''} style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="dateAdopted"
                                   label="Date Adopted"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   type="date"  // set the input type to date
                                   InputLabelProps={{shrink: true}}  // this will shrink the label when the user selects a date
                                   onChange={(event) => setAnimal({...animal, dateAdopted: event.target.value})}
                        />

                        <div style={{float: "left", marginLeft: "11px"}}>
                            <FormControlLabel
                                control={
                                    <Checkbox
                                        id="vaccine"
                                        checked={medicalRecord.vaccine}
                                        onChange={(event) => {
                                            setMedicalRecord({...medicalRecord, vaccine: event.target.checked});
                                        }}
                                    />
                                }
                                label="Vaccine"
                                sx={{mb: 2}}
                            />
                        </div>
                        <div style={{float: "left", marginLeft: "11px"}}>
                            <FormControlLabel
                                control={
                                    <Checkbox
                                        id="specialCare"
                                        checked={medicalRecord.specialCare}
                                        onChange={(event) => {
                                            setMedicalRecord({...medicalRecord, specialCare: event.target.checked});
                                        }}
                                    />
                                }
                                label="Special Care"
                                sx={{mb: 2}}
                            />
                        </div>

                        <TextField value={medicalRecord.additionalInformation} style={{color: "#2471A3", fontWeight: "bold"}}
                                   id="additionalInformation"
                                   label="Additional Information"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   onChange={(event) => setMedicalRecord({
                                       ...medicalRecord,
                                       additionalInformation: event.target.value
                                   })}
                        />

                    </form>
                </CardContent>
                <CardActions></CardActions>
            </Card>
        </Container>
    );
};