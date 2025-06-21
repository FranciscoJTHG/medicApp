export const RootProviders = ({ children }) => {
// proveedor de todos los provider
    return <>
        <AuthProvider>
            {children}
        </AuthProvider>
    </>
}