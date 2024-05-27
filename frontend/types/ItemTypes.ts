export type ItemIngredients = {
    itemIngredientId: number,
    isDefault: boolean,
    ingredientDTO: Ingredients,
    quantity: number
}

export type BaseItem = {
    itemId: number
    name: string
    description: string
    image: string
    price: number
    restaurantId: number
    categoryId: number
}

export type Ingredients = {
    ingredientId: number
    name: string
    price: number
    restaurantId: number
}

export type Item = {
    item: BaseItem
    itemIngredients: ItemIngredients[]
}
