import { Separator } from "@/components/ui/separator";
import FoodCard from "@/components/menu/foodCard";

export default function Home() {
    return (
        <div className="grid grid-cols-3">
            <div className="h-full p-5">
                <h1 className="text-2xl font-semibold">Menu Items</h1>
                <Separator />
                <div className="mt-5">
                    <ul className="flex flex-col mt-2 gap-2">
                        <li>Breakfast</li>
                        <li>Lunch</li>
                        <li>Dinner</li>
                    </ul>
                </div>
            </div>
            <div className="col-span-2 h-full">
                {
                    Array(10).fill(0).map((_, index) => (
                        <FoodCard key={index} />
                    ))
                }

            </div>
        </div>
    );
}
