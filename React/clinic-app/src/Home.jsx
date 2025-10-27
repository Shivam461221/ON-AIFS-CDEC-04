import About from "./components/About";
import Booking from "./components/Booking";
import Client from "./components/Client";
import Contact from "./components/Contact";
import Slider from "./components/Slider";
import Team from "./components/Team";
import Treatment from "./components/Treatment";

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