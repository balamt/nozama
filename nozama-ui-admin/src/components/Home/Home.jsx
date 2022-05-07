import React from "react";
import { FaArrowCircleDown } from "react-icons/fa";

function Home() {
  return (
    <div className="flex-col">
      <h2 className="text-2xl p-2">Home</h2>
      <div className="flex justify-start p-1">
        <div className="border-2 p-1 w-80">
          <div className="flex justify-between p-2 bg-slate-600 text-white">
            <div className="text-lg">Orders</div>
            <div>
              <FaArrowCircleDown size={22} className="text-teal-300" />
            </div>
          </div>
          <div className="flex justify-center bg-slate-200">
            <ul>
              <li>001 - Bob - 2000 ₹</li>
              <li>002 - Bob - 290 ₹ </li>
              <li>003 - Bob - 290 ₹ </li>
              <li>004 - Bob - 290 ₹ </li>
              <li>005 - Bob - 290 ₹ </li>
            </ul>
          </div>
          <div className="flex justify-end bg-slate-400 px-4">
            1, 2, 3 ... 4
          </div>
        </div>
        <div className="border-2 p-1 w-80">
          <div className="flex justify-between p-2 bg-slate-600 text-white">
            <div className="text-lg">Products - Out of Stock </div>
            <div>
              <FaArrowCircleDown size={22} className="text-teal-300" />
            </div>
          </div>
          <div className="flex justify-center bg-slate-200">
            <ul>
              <li>GC002 - Egg - 2</li>
              <li>CL001 - Black Jean - 10</li>
              <li>GC003 - Dhal - 8</li>
              <li>CL003 - Black Jean - 10</li>
              <li>CL004 - Blue Jean - 8</li>
            </ul>
          </div>
          <div className="flex justify-end bg-slate-400 px-4">
            1, 2, 3 ... 4
          </div>
        </div>
        <div className="border-2 p-1 w-80">
          <div className="flex justify-between p-2 bg-slate-600 text-white">
            <div className="text-lg">Shipment </div>
            <div>
              <FaArrowCircleDown size={22} className="text-teal-300" />
            </div>
          </div>
          <div className="flex justify-center bg-slate-200">
            <ul>
              <li>011 - Bob - Mumbai</li>
              <li>022 - John - Chennai</li>
              <li>013 - Bob - Goa</li>
              <li>034 - Bob - Gujarat</li>
              <li>015 - Bob - Delhi</li>
            </ul>
          </div>
          <div className="flex justify-end bg-slate-400 px-4">
            1, 2, 3 ... 4
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
