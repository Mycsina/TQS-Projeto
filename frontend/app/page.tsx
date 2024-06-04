import { Separator } from "@/components/ui/separator";
import {
    Pagination,
    PaginationItem,
    PaginationPrevious,
    PaginationLink,
    PaginationNext,
    PaginationContent,
    PaginationEllipsis
} from "@/components/ui/pagination";
import FoodCard from "@/components/menu/foodCard";
import RestauranteCard from "@/components/menu/restauranteCard";
import { get_restaurants } from "@/server/Restaurants";
import Link from "next/link";
import { getRestaurantMenu } from "@/server/Items";

export default async function Home(props: {
    searchParams: {
        r: number, // Last Restaurant
        s: number  // Selected Restaurant
    }
}) {
    const data = await get_restaurants();

    if (data === null) {
        return (
            <div className="flex">
                <h1 className="text-4xl text-red-600">Failed to get Restaurants</h1>
            </div>
        )
    }

    let idx = data.length;

    if (idx > 5) {
        idx = 5;
    }

    const sel = props.searchParams.s || data[0].restaurantId;
    const res = props.searchParams.r || (data.length < 5 ? data.length : 5);
    const partition = res > 5 ? res - 5 : 0;
    const restaurants = data.slice(partition, res);

    const menu = await getRestaurantMenu(sel);

    if (menu === null) {
        return (
            <div className="flex">
                <h1 className="text-4xl text-red-600">Failed to get menu</h1>
            </div>
        )
    }

    return (
        <div className="grid grid-cols-3 mx-auto w-fit">
            <div className="h-fit p-5 fixed w-96">
                <h1 className="text-4xl font-semibold mb-2">Restaurants</h1>
                <Separator />
                <div className="mt-5 flex flex-col gap-8">
                    {
                        restaurants.map((restaurant, index) => (
                            <Link href={`/?s=${restaurant.restaurantId}&r=${res}`} key={index}>
                                <RestauranteCard restaurant={restaurant} selected={sel} />
                            </Link>
                        ))
                    }
                </div>
                {data.length > 5 &&
                    <Pagination className="mt-5">
                        <PaginationContent>
                            {res > 5 &&
                                <PaginationItem>
                                    <PaginationPrevious href={`/?s=${sel}&r=${res > 5 ? res - 5 : 4}`} />
                                </PaginationItem>
                            }
                            <PaginationItem>
                                <PaginationLink href={`/?s=${sel}&r=5`}>1</PaginationLink>
                            </PaginationItem>
                            <PaginationItem>
                                <PaginationEllipsis />
                            </PaginationItem>
                            <PaginationItem>
                                <PaginationLink href={`/?s=${sel}&r=${data.length}`}>Last</PaginationLink>
                            </PaginationItem>
                            {data.length > res &&
                                <PaginationItem>
                                    <PaginationNext href={`/?s=${sel}&r=${+res + 5}`} />
                                </PaginationItem>
                            }
                        </PaginationContent>
                    </Pagination>
                }
            </div>
            <div className="mt-12 col-span-2 h-full w-fit col-start-2">
                {
                    menu.map((item, index) => (
                        <FoodCard key={index} showButton={true} item={item} />
                    ))
                }
            </div>
        </div>
    );
}
