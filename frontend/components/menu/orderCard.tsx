import { Separator } from "@/components/ui/separator";
import { ItemIngredients } from "@/types/ItemTypes";
import { Order } from "@/types/OrderTypes";

export default function OrderCard(props: { order: Order }) {
    const map = new Map(Object.entries(props.order.itemIngredients));
    let ingredients = new Array(map.size);

    let i = 0;
    while (i < map.size) {
        ingredients.push(map.entries().next());
        i++;
    }

    return (
        <div className="bg-zinc-100 dark:bg-gray-950 flex flex-col p-5 rounded shadow-lg w-[500px] min-h-52">
            <div className="flex justify-between">
                <h1 className="text-2xl font-semibold">#{props.order.order.orderId}</h1>
                <p className="text-xl font-semibold">Order Time: {new Date(props.order.order.scheduledTime).toLocaleTimeString()}</p>
            </div>
            <Separator />
            <div className="my-5">
                {
                    ingredients.map((ingredient, index) => {
                        const list = map.get(ingredient.value[0]) as ItemIngredients[];
                        return (
                            <div key={index}>
                                <h2 className="text-xl font-bold mb-2">{ingredient.value[0]}</h2>
                                {
                                    list.map((ing, idx) => {
                                        return (
                                            <div key={idx} className="flex items-center justify-between ps-2">
                                                <p className="font-semibold">{ing.ingredientDTO.name}</p>
                                                <p>{ing.quantity}</p>
                                            </div>
                                        )
                                    })
                                }
                            </div>
                        )
                    })
                }

            </div>
            <div className="flex justify-between items-center">
                <p>Order Method: <span className="text-green-500">{props.order.order.pickupMethod}</span></p>
            </div>
        </div>
    )
}
