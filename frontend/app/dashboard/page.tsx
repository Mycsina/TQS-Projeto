import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";

export default function DashBoard() {
    return (
        <div className="flex flex-col">
            <h1 className="font-bold text-2xl mb-5">Incoming Orders</h1>
            <div className="grid grid-cols-2 gap-y-8">
                {
                    Array(10).fill(0).map((_, index) => (
                        <div key={index} className="bg-zinc-100 flex flex-col p-5 rounded shadow-lg w-[500px]">
                            <div className="flex justify-between">
                                <h1 className="text-2xl font-semibold">Customer</h1>
                                <p className="text-xl font-semibold">Total $10</p>
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

                    ))
                }

            </div>
        </div>
    );
}
