import logo from './logo.png';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button } from 'react-bootstrap';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <div class="row">
          Test
          <div class="col"> Bob </div>
          <div class="col"> Bob 2</div>
          <div class="form-field"> 
          <input type="tel" value="2434323" id="ere"></input>
          <Button variant="primary                                                                          ">Click me</Button>
          </div>
        </div>
      </header>
    </div>
  );
}

export default App;
