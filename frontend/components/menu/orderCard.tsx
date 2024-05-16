import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";

export default function OrderCard() {
    return (
        <div className="bg-zinc-100 dark:bg-gray-950 flex flex-col p-5 rounded shadow-lg w-[500px] min-h-52">
            <div className="flex justify-between">
                <h1 className="text-2xl font-semibold">#12345</h1>
                <p className="text-xl font-semibold">Time Elapsed: 1m 10s</p>
            </div>
            <Separator />
            <div className="my-5">
                <p>Burger</p>
                <p>Extra fries</p>
                <p>Drink</p>
            </div>
            <div className="flex justify-between items-center">
                <p>Status: <span className="text-green-500">Preparing</span></p>
                <Button className="">Mark as Done</Button>
            </div>
        </div>
    )
}
