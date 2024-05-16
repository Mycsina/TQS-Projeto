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

export default function Home() {
    return (
        <div className="grid grid-cols-3 mx-auto w-fit">
            <div className="h-fit p-5 fixed w-96 bg-slate-100 dark:bg-slate-900 rounded-md shadow-lg">
                <h1 className="text-4xl font-semibold mb-2">Restaurants</h1>
                <Separator />
                <div className="mt-5 flex flex-col gap-8">
                    {
                        Array(5).fill(0).map((_, index) => (
                            <RestauranteCard key={index} />
                        ))
                    }
                </div>
                <Pagination className="mt-5">
                    <PaginationContent>
                        <PaginationItem>
                            <PaginationPrevious href="#" />
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationLink href="#">1</PaginationLink>
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationEllipsis />
                        </PaginationItem>
                        <PaginationItem>
                            <PaginationNext href="#" />
                        </PaginationItem>
                    </PaginationContent>
                </Pagination>
            </div>
            <div className="col-span-2 h-full w-fit col-start-2">
                {
                    Array(10).fill(0).map((_, index) => (
                        <FoodCard key={index} />
                    ))
                }
            </div>
        </div>
    );
}
