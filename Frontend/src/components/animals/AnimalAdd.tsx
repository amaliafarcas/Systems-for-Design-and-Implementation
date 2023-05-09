import {
    Autocomplete,
    Box,
    Button,
    Card,
    CardActions,
    CardContent, Checkbox,
    Container, FormControl, FormControlLabel, FormLabel,
    IconButton, InputLabel, MenuItem, OutlinedInput, Select,
    Snackbar,
    TextField, Theme, Tooltip,
    Typography, useTheme
} from "@mui/material";
import React, {useCallback, useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";
import {BACKEND_API_URL} from "../../constants";
import MuiAlert, {AlertProps} from "@mui/material/Alert";
import AddIcon from "@mui/icons-material/Add";
import {Shelter} from "../../models/Shelter";
import { debounce } from "lodash";


const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(
    props,
    ref,
) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export const AnimalAdd = () => {

    const navigate = useNavigate();

    const [errorMessage, setErrorMessage] = useState("");
    const [showNotification, setShowNotification] = useState(false);

    const [animal, setAnimal] = useState({
        name: "",
        microchipNumber: 0,
        shelter: 0,
        dayBroughtIn: "",
        dateAdopted: ""
    });

    const [medicalRecord, setMedicalRecord] = useState({
        dateOfBirth: "",
        age: 0,
        weight: 0,
        vaccine: false,
        specialCare: false,
        additionalInformation: ""
    });

    const addAnimal = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        try {
            const newMedicalRecord = await axios.post(`${BACKEND_API_URL}/medicalRecord`, medicalRecord);
            const newAnimal = {
                ...animal,
                medicalRecord: newMedicalRecord.data.id
            };
            console.log(newMedicalRecord.data.id);
            console.log(newAnimal.shelter);
            console.log(newAnimal);
            await axios.post(`${BACKEND_API_URL}/animal`, newAnimal);
            navigate("/animal");
        } catch (error) {
            console.log(error);
            setErrorMessage("Animal could not be added. Make sure the information is correct. ");
            setShowNotification(true);
        }
    };

    const [page, setPage] = useState(1);
    const [pageSize, setPageSize] = useState(10);

    const [shelters, setShelters] = useState<Shelter[]>([]);

    const fetchSuggestions = async (query: string) => {
        try {
            let url = `${BACKEND_API_URL}/shelter?page=${page}&size=${pageSize}`;
            const response = await fetch(url);
            const { content, pageable, last, totalPages, totalElements,
                size, number, sort,  first, numberOfElements, empty } = await response.json();
            setShelters(content);
        } catch (error) {
            console.error("Error fetching suggestions:", error);
        }
    };

    const debouncedFetchSuggestions = useCallback(debounce(fetchSuggestions, 500), []);

    useEffect(() => {
        return () => {
            debouncedFetchSuggestions.cancel();
        };
    }, [debouncedFetchSuggestions]);

    const handleInputChange = (event: any, value: any, reason: any) => {
        console.log("input", value, reason);

        if (reason === "input") {
            debouncedFetchSuggestions(value);
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

                    <form onSubmit={addAnimal}>
                        <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', paddingBlockEnd: 3}}>
                            <IconButton component={Link} sx={{mr: 3}} to={`/animal`}>
                                <ArrowBackIcon/>
                            </IconButton>{" "}
                            <Typography variant="h6" sx={{flexGrow: 1, textAlign: 'center'}}>
                                <b>Add New Shelter</b>
                            </Typography>

                            <Button type="submit" color="inherit" sx={{color: 'black'}}>
                                <Tooltip title="Add" arrow>
                                    <AddIcon/>
                                </Tooltip>
                            </Button>
                        </Box>
                        <TextField style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="name"
                                   label="Name"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   onChange={(event) => setAnimal({...animal, name: event.target.value})}
                        />

                        <TextField style={{color: "#2471A3", fontWeight: "bold"}}
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


                        <TextField style={{color: "#2471A3", fontWeight: "bold"}}
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

                        <TextField style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="microchipNumber"
                                   label="Microchip Number"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   onChange={(event) => setAnimal({
                                       ...animal,
                                       microchipNumber: parseInt(event.target.value)
                                   })}
                        />

                        <Autocomplete
                            id="shelterId"
                            options={shelters}
                            renderInput={(params) => <TextField {...params} label="Shelter" variant="outlined" />}
                            getOptionLabel={(option) => `${option.name} - ${option.city}`}
                            filterOptions={(options, state) => options.filter((option) => option.city.toLowerCase().includes(state.inputValue.toLowerCase()))}

                            onInputChange={handleInputChange}
                            onChange={(event, value) => {
                                if (value) {
                                    //console.log(value);
                                    setAnimal({ ...animal, shelter: value.id });
                                    console.log(animal);
                                }
                            }}

                        />

                        <TextField style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="dayBroughtIn"
                                   label="Day Brought In"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   type="date"  // set the input type to date
                                   InputLabelProps={{shrink: true}}  // this will shrink the label when the user selects a date
                                   onChange={(event) => setAnimal({...animal, dayBroughtIn: event.target.value})}
                        />

                        <TextField style={{color: "#2471A3", fontWeight: 'bold'}}
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

                        <TextField style={{color: "#2471A3", fontWeight: "bold"}}
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