import { Address } from "./UserTypes"

export type Restaurant = {
    restaurantId: number,
    name: string,
    description: string,
    phoneNumber: number,
    openingTime: string,
    closingTime: string,
    state: string,
    address: Address,
    menuId: number,
    managerId: number
}

export type LocalTime = {
    hour: number,
    minute: number,
    second: number,
    nano: number
}

