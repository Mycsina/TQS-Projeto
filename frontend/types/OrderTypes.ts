import { ItemIngredients } from "./ItemTypes"
import { Address } from "./UserTypes"

export enum pickupMethod {
    AT_RESTAURANT,
    TO_GO,
    DELIVERY
}

export type Order = {
    order: BaseOrder,
    itemIngredients: Map<string, ItemIngredients[]>
}

export type BaseOrder = {
    orderId: number,
    scheduledTime: string,
    deliveryAddress: Address,
    restaurantId: number,
    userId: number,
    pickupMethod: pickupMethod
}

