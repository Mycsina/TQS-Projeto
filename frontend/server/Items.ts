"use server";
import { API_URL } from "@/lib/consts";
import { Item } from "@/types/ItemTypes";

export async function getRestaurantMenu(id: number) {
    try {
        const data = await fetch(`${API_URL}/api/v1/items/restaurant/${id}`);

        if (data.status !== 200) {
            return null;
        }

        return await data.json() as Item[];
    } catch (e) {
        console.log(e);
        return null;
    }
}

export async function getItemByID(id: number) {
    try {
        const data = await fetch(`${API_URL}/api/v1/items/${id}`);

        if (data.status !== 200) {
            return null;
        }

        return await data.json() as Item;
    } catch (e) {
        console.log(e);
        return null;
    }
}

