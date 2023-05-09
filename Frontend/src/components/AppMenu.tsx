import { Box, AppBar, Toolbar, IconButton, Typography, Button } from "@mui/material";
import { Link, useLocation } from "react-router-dom";
import HomeIcon from '@mui/icons-material/Home';
import PetsIcon from '@mui/icons-material/Pets';
import {PeopleAlt} from "@mui/icons-material";

function Diversity3Icon() {
    return null;
}

export const AppMenu = () => {
    const location = useLocation();
    const path = location.pathname;

    return (
        <Box>
            <AppBar style={{backgroundColor:"black"}}>
                <Toolbar>
                    <IconButton
                        component={Link}
                        to="/shelter"
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="school"
                        sx={{ mr: 2 }}>
                        <HomeIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ mr: 5 }}>
                        Animal Shelters
                    </Typography>
                    <Button
                        variant={path.startsWith("/animal") ? "outlined" : "text"}
                        to="/animal"
                        component={Link}
                        color="inherit"
                        sx={{ mr: 5 }}
                        startIcon={<PetsIcon />}>
                        Animals
                    </Button>

                    <Button
                        variant={path.startsWith("/volunteer") ? "outlined" : "text"}
                        to="/volunteer"
                        component={Link}
                        color="inherit"
                        sx={{ mr: 5 }}
                        startIcon={<PeopleAlt />}>
                        Volunteers
                    </Button>

                    <Button
                        variant={path.startsWith("/shelter/report") ? "outlined" : "text"}
                        to="/shelter/report"
                        component={Link}
                        color="inherit"
                        sx={{ mr: 5 }}
                        startIcon={<Diversity3Icon />}>
                        Animals Vaccination Report
                    </Button>

                </Toolbar>
            </AppBar>
        </Box>
    );
};