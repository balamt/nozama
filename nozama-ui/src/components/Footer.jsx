import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCopyright } from '@fortawesome/free-solid-svg-icons';

//Multiple Class Components

export class Footer extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className='fixed-bottom footer-container'>
                <div className='navFooterCopyright'>
                    <ul className='ul_top_hypers'>
                        <li className="nav_first">
                            <a href="/tc" className="nav_a">Terms &amp; Conditions</a>
                        </li>
                        <li>
                            <a href="/pp" className="nav_a">Privacy Policy</a>
                        </li>
                    </ul>
                    <span className='copyright-item'>
                    Nozama or its affiliates, <FontAwesomeIcon icon={faCopyright} className='text-secondary' title="copyright" /> - {this.props.copyrightYear}</span>
                </div>
            </div>
        );
    }
}

export class SecureFooter extends Footer {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className='fixed-bottom footer-container'>
                <div className='navFooterCopyright'>
                    <ul className='ul_top_hypers'>
                        <li className="nav_first">
                            <a href="/preference" className="nav_a">Preference</a>
                        </li>
                        <li>
                            <a href="/tc" className="nav_a">Terms &amp; Conditions</a>
                        </li>
                        <li>
                            <a href="/pp" className="nav_a">Privacy Policy</a>
                        </li>
                    </ul>
                    <span className='copyright-item'>
                    Nozama or its affiliates, <FontAwesomeIcon icon={faCopyright} className='text-secondary' title="copyright" /> - {this.props.copyrightYear}</span>
                </div>
            </div>
        );
    }
}