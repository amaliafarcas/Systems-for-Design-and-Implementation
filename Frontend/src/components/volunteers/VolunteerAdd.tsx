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

export const VolunteerAdd = () => {

    const navigate = useNavigate();

    const [errorMessage, setErrorMessage] = useState("");
    const [showNotification, setShowNotification] = useState(false);

    const [volunteer, setVolunteer] = useState({
        name: "",
        code: 0,
        dateOfBirth: "",
        email: "",
        isSpecialist: false
    });


    const addVolunteer = async (event: { preventDefault: () => void }) => {
        event.preventDefault();
        try {
            await axios.post(`${BACKEND_API_URL}/volunteer`, volunteer);
            navigate("/volunteer");
        } catch (error) {
            console.log(error);
            setErrorMessage("Volunteer could not be added. Make sure the information is correct. ");
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

                    <form onSubmit={addVolunteer}>
                        <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', paddingBlockEnd: 3}}>
                            <IconButton component={Link} sx={{mr: 3}} to={`/volunteer`}>
                                <ArrowBackIcon/>
                            </IconButton>{" "}
                            <Typography variant="h6" sx={{flexGrow: 1, textAlign: 'center'}}>
                                <b>Add New Volunteer</b>
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
                                   onChange={(event) => setVolunteer({...volunteer, name: event.target.value})}
                        />

                        <TextField style={{color: "#2471A3", fontWeight: "bold"}}
                                   id="code"
                                   label="Code"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   onChange={(event) => setVolunteer({
                                       ...volunteer,
                                       code: parseInt(event.target.value)
                                   })}
                        />


                        <TextField style={{color: "#2471A3", fontWeight: "bold"}}
                                   id="dateOfBirth"
                                   label="Date of Birth"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   type="date"  // set the input type to date
                                   InputLabelProps={{shrink: true}}  // this will shrink the label when the user selects a date
                                   onChange={(event) => setVolunteer({...volunteer, dateOfBirth: event.target.value})}

                        />

                        <TextField style={{color: "#2471A3", fontWeight: 'bold'}}
                                   id="email"
                                   label="Email"
                                   variant="outlined"
                                   fullWidth
                                   sx={{mb: 2}}
                                   onChange={(event) => setVolunteer({
                                       ...volunteer,
                                       email: event.target.value
                                   })}
                        />


                        <div style={{float: "left", marginLeft: "11px"}}>
                            <FormControlLabel
                                control={
                                    <Checkbox
                                        id="isSpecialist"
                                        checked={volunteer.isSpecialist}
                                        onChange={(event) => {
                                            setVolunteer({...volunteer, isSpecialist: event.target.checked});
                                        }}
                                    />
                                }
                                label="Specialist"
                                sx={{mb: 2}}
                            />
                        </div>

                    </form>
                </CardContent>
                <CardActions></CardActions>
            </Card>
        </Container>
    );
};