import {Animal} from "./Animal";

export interface FullShelter{
    id: number;
    name: string;
    city: string;
    postalCode: number;
    phoneNumber: number;
    capacity: number;
    animals:Animal[];
}