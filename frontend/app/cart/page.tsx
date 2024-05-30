"use client";
import Link from "next/link";
import FoodCard from "@/components/menu/foodCard";
import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { getItemByID } from "@/server/Items";
import { useEffect, useState } from "react";
import { Item } from "@/types/ItemTypes";

export default function Cart() {
    const [total, setTotal] = useState(0);
    const [taxes, setTaxes] = useState(0);
    const [cartItems, setCartItems] = useState<Item[]>([]);
    const [cartMap, setMap] = useState<Map<any, any>>();

    useEffect(() => {
        let cart = localStorage.getItem("cart");
        let arr_items;
        let map = new Map();

        if (cart !== null) {
            arr_items = JSON.parse(cart) as number[];

            for (let i = 0; i < arr_items.length; i++) {
                let item = arr_items[i];
                if (map.get(item)) {
                    map.set(item, map.get(item) + 1);
                } else {
                    map.set(item, 1);
                }
            }
        }

        map.forEach((key, val) => {
            getItemByID(val).then(res => {
                if (res !== null) {
                    setCartItems(prev => [...prev, res])
                    setTotal(prev => prev + res.item.price * key * 0.77)
                    setTaxes(prev => prev + res.item.price * key * 0.23)
                }
            })
        })
        setMap(map);
    }, [])

    function deleteOrder(e: any) {
        e.preventDefault();
        localStorage.removeItem("cart");
        setCartItems([]);
        setTotal(0);
        setTaxes(0);
    }

    return (
        <div className="flex justify-between max-w-fit mx-auto">
            <div className="flex flex-col me-20 min-w-[808px]">
                <h1 className="text-4xl font-bold m-5">Items in cart</h1>
                <Separator className="mb-5" />
                {
                    cartItems.length === 0 ?
                        <p>No items in cart</p> :
                        cartItems.map((item, index) => (
                            <FoodCard item={item} showButton={false} key={index} quantity={cartMap?.get(item.item.itemId)} />
                        ))
                }
                <div className="w-full flex justify-end">
                    <Button className="w-52 font-bold me-5 mt-5" variant={"destructive"} onClick={deleteOrder}>Delete Order</Button>
                </div>
                <Separator className="mt-5" />
                <form action="" className="m-5">
                    <h1 className="text-4xl font-bold mb-5">Deliver to</h1>
                    <Label>Name</Label>
                    <Input className="bg-gray-900 text-white mb-3" placeholder="Name" />
                    <Label>Address</Label>
                    <Input className="bg-gray-900 text-white mb-3" placeholder="Address" />
                    <Label>Appartment</Label>
                    <Input className="bg-gray-900 text-white mb-3" placeholder="Appartment" />
                    <div className="flex gap-4">
                        <div>
                            <Label>City</Label>
                            <Input className="bg-gray-900 text-white mb-3" placeholder="City" />
                        </div>
                        <div>
                            <Label>Postal Code</Label>
                            <Input className="bg-gray-900 text-white mb-3" placeholder="Postal Code" />
                        </div>
                    </div>
                </form>
            </div>
            <div className="flex flex-col bg-gray-950 w-96 h-96 rounded-xl shadow-xl p-5 mt-10">
                <h1 className="text-4xl font-semibold mb-1">Cart</h1>
                <Separator />
                <div className="flex justify-between mt-8">
                    <h2 className="">Food total</h2>
                    <h2 className="">${total.toFixed(2)}</h2>
                </div>
                <div className="flex justify-between my-2">
                    <h2 className="">Taxes</h2>
                    <h2 className="">${taxes.toFixed(2)}</h2>
                </div>
                <div className="flex justify-between mb-4">
                    <h2 className="text-xl font-semibold">Cart total</h2>
                    <h2 className="text-xl font-semibold">${(total + taxes).toFixed(2)}</h2>
                </div>
                <div className="flex-grow"></div>
                <Link href="/order-success">
                    <Button className="w-full font-bold">Pay Now</Button>
                </Link>
            </div>
        </div>
    );
}
