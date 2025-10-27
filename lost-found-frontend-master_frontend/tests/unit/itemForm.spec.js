import { mount } from "@vue/test-utils";
import ItemForm from "../../src/components/ItemForm.vue";

describe("ItemForm", () => {
  it("disables submit when fields are empty", () => {
    const wrapper = mount(ItemForm);
    const button = wrapper.find("button");
    expect(button.element.disabled).toBe(true);
  });
});
