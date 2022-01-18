package in.nozama.service.dto.view;

/**
 * This Class is used for restricting the JSON Response sent.
 * Check the User.java for the Annotation @JsonView which makes uses of this class to restrict the fields
 */
public class UserModelView {
    public static class PublicView {}
    public static class ProtectedView extends PublicView {}
}
