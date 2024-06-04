"use client";
import Image from 'next/image';
import { Separator } from '@/components/ui/separator';
import { Button } from '@/components/ui/button';
import burger from '@/public/burger.jpeg';
import { Item } from '@/types/ItemTypes';

export default function FoodCard(props: { showButton: boolean, item: Item, quantity? : number}) {

    function addToCart() {
        const cart = localStorage.getItem("cart");

        let new_cart;
        if (cart !== null) {
            new_cart = JSON.parse(cart) as number[];
            new_cart.push(props.item.item.itemId)
        } else {
            new_cart = [props.item.item.itemId]
        }

        localStorage.setItem("cart", JSON.stringify(new_cart))
    }

    return (
        <div className="bg-slate-100 dark:bg-gray-950 m-5 flex rounded shadow-lg max-w-3xl">
            <Image src={burger} alt="Food Item" width={200} height={150} className="aspect-[4/3] rounded shadow-md h-full w-auto" />
            <div className="p-5 flex flex-col w-[512px]">
                <div className="flex justify-between">
                    <h1 className="text-2xl font-semibold">{props.item.item.name}</h1>
                    <p className="text-xl font-semibold">{props.quantity && `${props.quantity} x `}${props.item.item.price}</p>
                </div>
                <Separator />
                <p className="mt-5 mb-2">{props.item.item.description}</p>
                <div className="flex-grow"></div>
                {props.showButton &&
                    <Button className='font-semibold w-full' onClick={addToCart}>Add to Cart</Button>
                }
            </div>
        </div>
    );
}
