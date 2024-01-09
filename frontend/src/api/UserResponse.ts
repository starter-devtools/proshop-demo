
export interface LoginResponse {
    email: string;
    password: string;
}

export interface SignUpResponse {
    name: string;
    email: string;
    password: string;
    isAdmin: boolean;
}

export interface UserResponse {
    name: string;
    email: string;
    isAdmin: boolean;
}