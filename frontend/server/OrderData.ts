"use server";

import { Order } from "@/types/OrderTypes";
import { API_URL, API_VERSION } from "@/lib/consts";

export async function getAllOrders() {
  try {
    const data = await fetch(`${API_URL}/api/${API_VERSION}/orders`);

    if (data.status !== 200) {
      return null;
    }

    return await data.json() as Order[];
  } catch (e) {
    console.log(e);
    return null;
  }
}

export async function getInMaking() {
  try {
    const data = await fetch(`${API_URL}/api/${API_VERSION}/orders/inmaking`, { cache: 'no-store' });

    if (data.status !== 200) {
      return null;
    }

    return await data.json() as Order[];
  } catch (e) {
    console.log(e);
    return null;
  }
}
