import React from 'react';
import '../components/css/NozamaApp.css';

function Header(){
    const AppName="Nozama";
    return(
    <div className="App">
        <h1 class="app-title">{AppName}</h1>
    </div>
    );
}

export default Header;