import React, { Component } from 'react';

class Click extends Component {
    constructor(props){
        super(props);
    }
    state = { 
        count: 0
    } 
     UpdateClick=()=>{
         this.setState({count: this.state.count + 1});
     }
    render() { 
        const {count} = this.state;
        return (
            <button onClick={this.UpdateClick}>I clicked {count} time</button>
        );
    }
}
 
export default Click;