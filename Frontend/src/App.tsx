import React, {useState} from "react";
import "./App.css";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {AppMenu} from "./components/AppMenu";
import {AppHome} from "./components/AppHome";
import {SheltersShowAll} from "./components/shelters/AllShelters";
import {ShelterDetails} from "./components/shelters/ShelterDetails";
import {ShelterDelete} from "./components/shelters/ShelterDelete";
import {ShelterUpdate} from "./components/shelters/ShelterUpdate";
import {ShelterAdd} from "./components/shelters/ShelterAdd";
import {SheltersStatistics} from "./components/shelters/ShelterStatistics";
import {AnimalsShowAll} from "./components/animals/AllAnimals";
import {AnimalAdd} from "./components/animals/AnimalAdd";
import {AnimalUpdate} from "./components/animals/AnimalUpdate";
import {AnimalDelete} from "./components/animals/AnimalDelete";
import {AnimalDetails} from "./components/animals/AnimalDetails";
import {VolunteersShowAll} from "./components/volunteers/AllVolunteers";
import {VolunteerDetails} from "./components/volunteers/VolunteerDetails";
import {VolunteerAdd} from "./components/volunteers/VolunteerAdd";
import {VolunteerUpdate} from "./components/volunteers/VolunteerUpdate";
import {VolunteerDelete} from "./components/volunteers/VolunteerDelete";


function App() {
    const [count, setCount] = useState(0);

    return (

        <React.Fragment>
            <Router>
                <AppMenu/>
                <Routes>
                    {/*<Route path="/" element={<AppHome/>}/>*/}
                    <Route path="/shelter" element={<SheltersShowAll/>}/>
                    <Route path="/shelter/:shelterId" element={<ShelterDetails/>}/>
                    <Route path="/shelter/:shelterId/delete" element={<ShelterDelete/>}/>
                    <Route path="/shelter/:shelterId/edit" element={<ShelterUpdate/>}/>
                    <Route path="/shelter/add" element={<ShelterAdd/>}/>
                    <Route path="/shelter/report" element={<SheltersStatistics/>}/>

                    <Route path="/animal" element={<AnimalsShowAll/>}/>
                    <Route path="/animal/:animalId" element={<AnimalDetails/>}/>
                    <Route path="/animal/:animalId/delete" element={<AnimalDelete/>}/>
                    <Route path="/animal/:animalId/edit" element={<AnimalUpdate/>}/>
                    <Route path="/animal/add" element={<AnimalAdd/>}/>

                    <Route path="/volunteer" element={<VolunteersShowAll/>}/>
                    <Route path="/volunteer/:volunteerId" element={<VolunteerDetails/>}/>
                    <Route path="/volunteer/:volunteerId/delete" element={<VolunteerDelete/>}/>
                    <Route path="/volunteer/:volunteerId/edit" element={<VolunteerUpdate/>}/>
                    <Route path="/volunteer/add" element={<VolunteerAdd/>}/>

                </Routes>
            </Router>
        </React.Fragment>
    );
}

export default App;