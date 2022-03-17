import React, { Component } from 'react';

class Counter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            count: 0
        };
    }

    IncrementCount=()=>{
        this.setState({count: this.state.count + 1});
    }

    state = {  }
    render() { 
        const {count} = this.state;
        return ( 
            <button onMouseEnter={this.IncrementCount}>Counter Incremented to {count}</button>
         );
    }
}
 
export default Counter;