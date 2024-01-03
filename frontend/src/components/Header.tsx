import { FunctionComponent } from 'react'
import { Container, Nav, NavLink, Navbar, NavbarBrand, NavbarCollapse, NavbarToggle } from 'react-bootstrap';
import { FaShoppingCart, FaUser } from 'react-icons/fa';
import logo from '../assets/logo.png';
import { LinkContainer } from "react-router-bootstrap";

export const Header: FunctionComponent = () => {
  return (
    <header>
        <Navbar bg='dark' variant='dark' expand='md' collapseOnSelect>
            <Container>
                <LinkContainer to='/'>
                    <NavbarBrand>
                        <img src={logo} alt=''/>
                        ProShop
                    </NavbarBrand>
                </LinkContainer>
                <NavbarToggle aria-controls='basic-navbar-nav'/>
                <NavbarCollapse id='basic-navbar-nav'>
                    <Nav className='ms-auto'>
                        <LinkContainer to='/cart'>
                            <NavLink>
                                <FaShoppingCart/>
                                Cart
                            </NavLink>
                        </LinkContainer>
                        <LinkContainer to='/login'>
                            <NavLink>
                                <FaUser/>
                                Sign In
                            </NavLink>
                        </LinkContainer>
                    </Nav>
                </NavbarCollapse>
            </Container>
        </Navbar>
    </header>
  );
};