import {Animal} from "./Animal";

export interface Shelter{
    id: number;
    name: string;
    city: string;
    postalCode: number;
    phoneNumber: number;
    capacity: number;
}