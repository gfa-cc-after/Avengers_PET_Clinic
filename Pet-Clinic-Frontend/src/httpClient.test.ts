import { describe, expect, it, vi } from "vitest";
import { register, } from "./httpClient";

vi.mock("./httpClient", () => ({
  register: vi.fn(),
}));

describe("httpClient", () => {

  it("should call register", async () => {
    await register({
      email: "test",
      password: "test",
    });
    expect(register).toHaveBeenCalledTimes(1);
  });

});