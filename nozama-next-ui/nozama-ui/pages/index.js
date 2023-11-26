import Head from "next/head";
import Image from "next/image";
import Header from "../components/Header";
import logo from "../components/img/logo.png";
import SideBar from "../components/SideBar.jsx";

export default function Home() {
  return (
    <div>
      <Head>
        <title>Nozama | Next Reactive Shopping</title>
        <meta name="description" content="Next Reactive Shopping" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Header appName="Nozama" appLogo={logo} />
      <main className="flex justify-center bg-info">
        {/* Products, login, Account, orders, Shipping */}
        {/* Right Side bar */}
        <SideBar />
      </main>
    </div>
  );
}
