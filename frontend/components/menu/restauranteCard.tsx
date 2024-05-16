import Image from "next/image";
import { StarFilledIcon } from "@radix-ui/react-icons";
import burgerLogo from "@/public/burgerlogo.jpg";

export default function RestauranteCard() {
    return (
        <div className="flex rounded bg-slate-200 dark:bg-slate-950 shadow-md">
            <Image src={burgerLogo} alt={"Restaurant Logo"} width={100} height={100} className="aspect-square shadow-md rounded" />
            <div className="flex flex-col justify-start p-3">
                <h1 className="font-semibold">Burgir Restaurant</h1>
                <div className="flex items-center">
                    {
                        Array(5).fill(0).map((_, index) => (
                            <StarFilledIcon key={index} />
                        ))
                    }
                    <p className="text-zinc-600 text-sm ms-1">(5.0 average)</p>
                </div>
                <div className="flex-grow"></div>
                <p className="text-zinc-600 text-sm">0.246 Km away</p>
            </div>
        </div>
    )
}
