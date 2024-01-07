import { FunctionComponent } from 'react'
import { Badge, Container, Nav, NavLink, Navbar, NavbarBrand, NavbarCollapse, NavbarToggle } from 'react-bootstrap';
import { FaShoppingCart, FaUser } from 'react-icons/fa';
import logo from '../assets/logo.png';
import { LinkContainer } from "react-router-bootstrap";
import { useSelector } from 'react-redux';
import { CartResponse } from '../api/CartResponse';

export const Header: FunctionComponent = () => {
    const { cartItems } = useSelector((state) => state.cart);

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
                                <FaShoppingCart/> Cart
                                {
                                    cartItems.length > 0 && (
                                        <Badge pill bg='success' style={{marginLeft: '5px'}}>
                                            { cartItems.reduce((a, c) => a + c.quantity, 0)}
                                        </Badge>
                                    )
                                }
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