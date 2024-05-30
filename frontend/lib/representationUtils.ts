import { LocalTime } from "@/types/RestaurantTypes";

export function representLocalTime(time: LocalTime) {
    // Second and nanosecond are ignored due to the desired use for this representation
    return `${time.hour}:${time.minute}`
}
