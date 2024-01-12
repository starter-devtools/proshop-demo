import { FunctionComponent } from 'react'
import { Badge, Container, Nav, NavDropdown, NavLink, Navbar, NavbarBrand, NavbarCollapse, NavbarToggle } from 'react-bootstrap';
import { FaShoppingCart, FaUser } from 'react-icons/fa';
import logo from '../assets/logo.png';
import { LinkContainer } from "react-router-bootstrap";
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useLogoutMutation } from '../slices/user-api-slice';
import { logout } from '../slices/auth-slice';

export const Header: FunctionComponent = () => {
    const { cartItems } = useSelector((state) => state.cart);
    const { userInfo } = useSelector((state) => state.auth);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [logoutRequest] = useLogoutMutation();

    const logoutHandler = async () => {
        try {
            await logoutRequest(null);
            dispatch(logout());
            navigate("/");
        } catch(error) {
            console.error(error);
        }
    }

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
                        {userInfo?.accessToken?.data ? (
                            <>
                                <NavDropdown title={userInfo.email} id='email'>
                                    <LinkContainer to='/profile'>
                                        <NavDropdown.Item>Profile</NavDropdown.Item>
                                    </LinkContainer>
                                    <NavDropdown.Item onClick={logoutHandler}>Logout</NavDropdown.Item>
                                </NavDropdown>
                            </>   
                        ): (
                        <LinkContainer to='/login'>
                            <NavLink>
                                <FaUser/>
                                Sign In
                            </NavLink>
                        </LinkContainer>
                        )}
                    </Nav>
                </NavbarCollapse>
            </Container>
        </Navbar>
    </header>
  );
};