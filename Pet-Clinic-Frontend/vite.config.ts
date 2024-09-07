import react from "@vitejs/plugin-react-swc";
import { defineConfig } from "vite";

const threshold: number = 70 as const;


export default defineConfig({
  plugins: [react()],
});
