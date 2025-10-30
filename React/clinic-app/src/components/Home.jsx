import About from "./About";
import Booking from "./Booking";
import Client from "./Client";
import Contact from "./Contact";
import Slider from "./Slider";
import Team from "./Team";
import Treatment from "./Treatment";

export default function Home() {
    return <>
        <Slider/>
        <Booking/>
        <About/>
        <Treatment/>
        <Team/>
        <Client/>
        <Contact/>
    </>
}