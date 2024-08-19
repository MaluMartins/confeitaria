import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { LoginForm } from '../components/loginForm/loginForm';
import { Home } from '../pages/home/home';
import { PrivateRoute } from './PrivateRoute';
import { MainLayout } from '../pages/main/MainLayout';
import { Orders } from '../pages/orders/orders';
import { ProductsPage } from '../pages/products/products';
import { Estoque } from '../pages/estoque/estoque';

export const AppRoutes: React.FC = () => {
  return (

    <Routes>
      <Route path="/login" element={<LoginForm />} />
      <Route path="/" element={<MainLayout />}>
        <Route path="/home" element={<PrivateRoute element={<Home />} />} />
        <Route path="/orders" element={<PrivateRoute element={<Orders />} />} />
        <Route path="/products" element={<PrivateRoute element={<ProductsPage />} />} />
        <Route path="/estoque" element={<PrivateRoute element={<Estoque />} />} />
      </Route>
    </Routes>
  );
};
