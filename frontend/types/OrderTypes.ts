import { Item } from "./ItemTypes"
import { Address } from "./UserTypes"

export enum pickupMethod {
    AT_RESTAURANT,
    TO_GO,
    DELIVERY
}

export type Order = {
    order: BaseOrder,
    items: Item[]
}

export type BaseOrder = {
    orderId: number,
    price: number,
    scheduledTime: string,
    deliveryAddress: Address,
    restaurantId: number,
    userId: number,
    pickupMethod: pickupMethod
}

