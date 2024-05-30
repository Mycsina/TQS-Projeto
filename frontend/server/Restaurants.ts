"use server";

import { API_URL } from "@/lib/consts";
import { Restaurant } from "@/types/RestaurantTypes";

export async function get_restaurants() {
    try {
        const data = await fetch(`${API_URL}/api/v1/restaurants`);

        if (data.status !== 200) {
            return null;
        }

        return await data.json() as Restaurant[];
    } catch (e) {
        console.log(e);
        return null;
    }
}
