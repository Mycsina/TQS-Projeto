import Image from 'next/image';
import { Separator } from '@/components/ui/separator';
import { Button } from '@/components/ui/button';
import burger from '@/public/burger.jpeg';

export default function FoodCard() {
    return (
        <div className="bg-slate-100 dark:bg-slate-900 m-5 flex rounded shadow-lg max-w-3xl">
            <Image src={burger} alt="Food Item" width={200} height={150} className="aspect-[4/3] rounded shadow-md h-full w-auto" />
            <div className="p-5 flex flex-col">
                <div className="flex justify-between">
                    <h1 className="text-2xl font-semibold">Burger</h1>
                    <p className="text-xl font-semibold">$10</p>
                </div>
                <Separator />
                <p className="mt-5 mb-2">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam, voluptatum.</p>
                <div className="flex-grow"></div>
                <Button className='font-semibold'>Add to Cart</Button>
            </div>
        </div>
    );
}
