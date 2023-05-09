import {MedicalRecord} from "./MedicalRecord";
import {Shelter} from "./Shelter";
import {Volunteer} from "./Volunteer";

export interface Animal{
    id:number;
    name:string;
    medicalRecord:MedicalRecord;
    microchipNumber:number;
    shelter:Shelter;
    dayBroughtIn:string;
    dateAdopted: string;
    volunteersAssigned: {
        assignmentDay: string;
        t: Volunteer;
    }[];
}