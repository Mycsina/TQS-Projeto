"use client"

import { Separator } from "@/components/ui/separator";
import { ItemIngredients } from "@/types/ItemTypes";
import { Order } from "@/types/OrderTypes";
import { useState } from "react";

export default function OrderCard(props: { order: Order }) {
  const items = props.order.items;
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const closeDropdown = () => {
    setIsOpen(false);
  };
  return (
    <div className="bg-zinc-100 dark:bg-gray-950 flex flex-col p-5 rounded shadow-lg w-[500px] min-h-52">
      <div className="flex justify-between">
        <h1 className="text-2xl font-semibold">#{props.order.order.orderId}</h1>
        <p className="text-xl font-semibold">Order Time: {new Date(props.order.order.scheduledTime).toLocaleTimeString()}</p>
      </div>
      <Separator />
      <div className="my-5">
        {
          items.map((item, index) => {
            const itemIngredients = item.itemIngredients;
            return (
              <div key={index}>
                <h2 className="text-xl font-bold mb-2">{item.item.name}</h2>
                {
                  itemIngredients.map((itemIngredient, idx) => {
                    return (
                      <div key={idx} className="flex items-center justify-between ps-2">
                        <p className="font-semibold">{itemIngredient.ingredientDTO.name}</p>
                        <p>{itemIngredient.quantity}</p>
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
        <div className="relative inline-block">
          <button
            type="button"
            className="px-4 py-2 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm inline-flex items-center"
            onClick={toggleDropdown}
          >
            Actions <svg className="w-2.5 h-2.5 ml-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
              <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4" />
            </svg>
          </button>
          {isOpen && (
            <div className="origin-top-right absolute right-0 mt-2 w-44 rounded-lg shadow-lg bg-white ring-1 ring-black ring-opacity-5">
              <ul role="menu" aria-orientation="vertical" aria-labelledby="actions-menu">
                <li>
                  <a
                    href="#"
                    // yellow button
                    className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 bg-yellow-500 rounded-t-lg"
                    onClick={() => {
                      console.log('Suspend Order');
                      closeDropdown();
                    }}
                  >
                    Suspend Order
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 bg-red-500 rounded-b-lg"
                    onClick={() => {
                      console.log('Cancel Order');
                      closeDropdown();
                    }}
                  >
                    Cancel Order
                  </a>
                </li>
              </ul>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
