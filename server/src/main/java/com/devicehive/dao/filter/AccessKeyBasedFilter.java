package com.devicehive.dao.filter;

import com.devicehive.model.AccessKeyPermission;

import java.util.*;

public class AccessKeyBasedFilter {

    private Set<String> deviceGuids;

    private Set<Long> networkIds;

    public AccessKeyBasedFilter(Collection<String> deviceGuids, Collection<Long> networkIds) {
        this.deviceGuids = new HashSet<>(deviceGuids);
        this.networkIds = new HashSet<>(networkIds);
    }

    public Set<String> getDeviceGuids() {
        return Collections.unmodifiableSet(deviceGuids);
    }

    public Set<Long> getNetworkIds() {
        return Collections.unmodifiableSet(networkIds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessKeyBasedFilter that = (AccessKeyBasedFilter) o;

        if (deviceGuids != null ? !deviceGuids.equals(that.deviceGuids) : that.deviceGuids != null) return false;
        if (networkIds != null ? !networkIds.equals(that.networkIds) : that.networkIds != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deviceGuids != null ? deviceGuids.hashCode() : 0;
        result = 31 * result + (networkIds != null ? networkIds.hashCode() : 0);
        return result;
    }

    public static Set<AccessKeyBasedFilter> createExtraFilters(Set<AccessKeyPermission> permissionSet) {
        if (permissionSet == null) {
            return null;
        }
        Set<AccessKeyBasedFilter> result = new HashSet<>();
        for (AccessKeyPermission akp : permissionSet) {
            result.add(new AccessKeyBasedFilter(akp.getDeviceGuidsAsSet(), akp.getNetworkIdsAsSet()));
        }
        return result;
    }
}