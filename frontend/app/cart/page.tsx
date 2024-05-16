import FoodCard from "@/components/menu/foodCard";
import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export default function Cart() {
    return (
        <div className="flex justify-between max-w-fit mx-auto">
            <div className="flex flex-col me-20">
                <h1 className="text-4xl font-bold m-5">Items in cart</h1>
                <Separator className="mb-5" />
                <FoodCard showButton={false} />
                <FoodCard showButton={false} />
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
                    <h2 className="">$20</h2>
                </div>
                <div className="flex justify-between my-2">
                    <h2 className="">Delivery</h2>
                    <h2 className="">$1.5</h2>
                </div>
                <div className="flex justify-between mb-4">
                    <h2 className="text-xl font-semibold">Cart total</h2>
                    <h2 className="text-xl font-semibold">$21.5</h2>
                </div>
                <div className="flex-grow"></div>
                <Button className="w-full font-bold">Pay Now</Button>
            </div>
        </div>
    );
}
