import Image from "next/image";
import burgerLogo from "@/public/burgerlogo.jpg";
import { Restaurant } from "@/types/RestaurantTypes";

export default function RestauranteCard(props: { restaurant: Restaurant, selected: number }) {

    return (
        <div className={`flex rounded bg-slate-200 ${props.selected === props.restaurant.restaurantId ? "dark:bg-gray-950" : "dark:bg-slate-950"} shadow-md`}>
            <Image src={burgerLogo} alt={"Restaurant Logo"} width={100} height={100} className="aspect-square shadow-md rounded" />
            <div className="flex flex-col justify-start p-3">
                <h1 className="font-semibold">{props.restaurant.name}</h1>
                <div className="flex-grow"></div>
                <p className="text-zinc-600 text-sm">Phone: {props.restaurant.phoneNumber}</p>
                <p className="text-zinc-600 text-sm">Open from {props.restaurant.openingTime.slice(0, 5)} to {props.restaurant.closingTime.slice(0, 5)}</p>
            </div>
        </div>
    )
}
