import logo from './components/img/logo.png';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button } from 'react-bootstrap';

import './components/css/NozamaApp.css';
import Header from './components/Header.jsx';
import {Footer} from './components/Footer.jsx';
import Click from './components/Click.js';
import Counter from './components/Counter.jsx';

function NozamaApp() {
  return (
    <div className='NozamaApp'>
      <Header />
      <Click />
      <Counter />
      <Footer />
    </div>
  );
}

export default NozamaApp;
