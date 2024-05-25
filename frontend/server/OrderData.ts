"use server";

import { Order } from "@/types/OrderTypes";
import { API_URL } from "@/lib/consts";

export async function getAllOrders() {
    const data = await fetch(`${API_URL}/api/order`);

    if (data.status !== 200) {
        return null;
    }

    return await data.json() as Order[];
}
